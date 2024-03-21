import {
  FruitLevelBadge,
  IeumLevenBadge,
  SeedLevelBadge,
  SessakLevelBadge,
  SmallTreeLevelBadge,
  TooSmallTreeLevelBadge,
  TreeLevelBadge,
} from '@/components/icons/LevelBadges';

export default function TearList() {
  return (
    <div>
      <SeedLevelBadge />
      <SessakLevelBadge />
      <SmallTreeLevelBadge />
      <TooSmallTreeLevelBadge />
      <TreeLevelBadge />
      <IeumLevenBadge />
      <FruitLevelBadge />
    </div>
  );
}
